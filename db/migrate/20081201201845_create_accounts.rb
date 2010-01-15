class CreateAccounts < ActiveRecord::Migration
  def self.up
    create_table :accounts do |t|
      t.string :name, :null => false
      t.string :identifier, :null => false
      t.string :account_type, :null => false
      t.decimal :initial_balance

      t.timestamps
    end
  end

  def self.down
    drop_table :accounts
  end
end
