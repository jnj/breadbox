class AddTransactionDateToTransactions < ActiveRecord::Migration
  def self.up
    add_column 'transactions', 'transaction_date', :date, :null => false
  end

  def self.down
    remove_column 'transactions', 'transaction_date'
  end
end
