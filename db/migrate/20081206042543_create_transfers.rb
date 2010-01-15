class CreateTransfers < ActiveRecord::Migration
  def self.up
    create_table :transfers do |t|
      t.integer :withdrawal_id
      t.integer :deposit_id

      t.timestamps
    end
  end

  def self.down
    drop_table :transfers
  end
end
