class Account < ActiveRecord::Base
  has_many :transactions
  has_many :stocks
  validates_presence_of :name
  validates_length_of :name, :minimum => 1
  validates_presence_of :identifier
  validates_length_of :identifier, :minimum => 1
  validates_presence_of :account_type
  validates_length_of :account_type, :minimum => 1
  validates_presence_of :initial_balance
  validates_numericality_of :initial_balance

  def recent_transactions(days_back)
    today = Date.today
    transactions_in_range(today - days_back, today)
  end

  def transactions_in_range(start_date, stop_date)
    transactions.find(:all, :conditions => ['transaction_date >= ? and transaction_date <= ?', start_date, stop_date], :order => 'transaction_date DESC')
  end
  
  # Returns all debits from the account as a hash from Category to
  # the sum for that Category. The amounts will be positive, even
  # though they are debits.
  def category_sums(txns)
    debits = txns.select {|t| t.debit?}
    debits.inject(Hash.new {|h,k| h[k] = 0.0}) do |hash, txn|
      hash[txn.category.name] += -txn.amount; hash
    end
  end
  
  def categories_with_percent_more_than(sums, pct)
    total = sums.values.inject(0) {|accum, val| accum += val}
    sums.inject({}) do |hash, entry|
      hash.store(*entry) if (entry[1] / total) * 100 > pct
      hash
    end
  end
end
