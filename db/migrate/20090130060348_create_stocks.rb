class CreateStocks < ActiveRecord::Migration
  def self.up
    create_table :stocks do |t|
      t.string :symbol, :null => false
      t.integer :num_shares, :null => false
      t.references :account, :null => false

      t.timestamps
    end
  end

  def self.down
    drop_table :stocks
  end
end
