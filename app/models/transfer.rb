class Transfer < ActiveRecord::Base
  belongs_to :withdrawal, :class_name => 'Transaction'
  belongs_to :deposit, :class_name => 'Transaction'
end
