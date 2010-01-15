class ChangeMonetaryColumnsToFloats < ActiveRecord::Migration
  def self.up
    change_column 'accounts', 'initial_balance', :float, :null => false
    change_column 'transactions', 'amount', :float, :null => false
  end

  def self.down
    change_column 'accounts', 'initial_balance', :integer, :limit => 10, :precision => 10, :scale => 0
    change_column 'transactions', 'amount', :integer, :limit => 10, :precision => 10, :scale => 0
  end
end
