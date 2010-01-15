class Transaction < ActiveRecord::Base
  belongs_to :account
  belongs_to :category
  validates_presence_of :description
  validates_presence_of :account_id
  validates_numericality_of :account_id
  validates_numericality_of :amount
  validates_confirmation_of :amount
  
  def debit?
    amount < 0
  end
end
