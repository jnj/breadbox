require 'lib/homebank'

class AccountsController < ApplicationController

  # GET /accounts
  # GET /accounts.xml
  def index
    @accounts = Account.find(:all)
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @accounts }
    end
  end

  # GET /accounts/1
  # GET /accounts/1.xml
  def show
    @account = Account.find(params[:id])

    sums = @account.category_sums(@account.recent_transactions(30))
    @sums = @account.categories_with_percent_more_than(sums, 3)
    
    chart_data = []
    @sums.each_with_index do |e, i|
      chart_data << [i, e[1]]
    end
    data_points = chart_data.map {|a| "[#{a[0]},#{a[1]}]"}.join(',')
    @chart_data = "[#{data_points}]"
  
    @quotes_map = @account.stocks.inject({}) do |hash, stock|
      hash[stock] = stock.price
      hash
    end   
  
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @account }
    end
  end

  # GET /accounts/new
  # GET /accounts/new.xml
  def new
    @account = Account.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @account }
    end
  end

  # GET /accounts/1/edit
  def edit
    @account = Account.find(params[:id])
  end

  # POST /accounts
  # POST /accounts.xml
  def create
    @account = Account.new(params[:account])

    respond_to do |format|
      if @account.save
        flash[:notice] = 'Account was successfully created.'
        format.html { redirect_to(@account) }
        format.xml  { render :xml => @account, :status => :created, :location => @account }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @account.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /accounts/1
  # PUT /accounts/1.xml
  def update
    @account = Account.find(params[:id])

    respond_to do |format|
      if @account.update_attributes(params[:account])
        flash[:notice] = 'Account was successfully updated.'
        format.html { redirect_to(@account) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @account.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /accounts/1
  # DELETE /accounts/1.xml
  def destroy
    @account = Account.find(params[:id])
    @account.destroy

    respond_to do |format|
      format.html { redirect_to(accounts_url) }
      format.xml  { head :ok }
    end
  end

  def import_from_file
    file = params[:file]
    reader = HomeBank::TransactionCsvReader.new(file.readlines)
    import_result = reader.read

    import_result.transactions.each do |hb| 
      # TODO: handle internal transfers
      if !hb.internal_transfer?
        txn = make_transaction_from_hb_record(hb)
        txn.save
      end
    end

    flash[:notice] = 'File uploaded'
    @account = Account.find(params[:account_id])
    respond_to do |format|
      format.html { redirect_to(@account) }
      format.xml  { head :ok }
    end
  end
  
  # Converts a HomeBank::HbTransaction into a Transaction. The
  # Category will be fetched from the database or created in the
  # database as needed.
  def make_transaction_from_hb_record(hb_txn)
    category = hb_txn.category
    category = 'Misc' if hb_txn.empty_category?
    matched_category = Category.find_or_create(category)
    
    txn = Transaction.new do |t|
      t.amount = hb_txn.amount.to_f
      t.description = hb_txn.description
      date_str = "#{hb_txn.month}/#{hb_txn.day}/#{hb_txn.year}"
      t.transaction_date = Date.parse(date_str)
      t.account_id = params[:account_id]
      t.category = matched_category
    end
  end
end
