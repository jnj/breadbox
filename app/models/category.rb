class Category < ActiveRecord::Base
  has_many :transactions
  validates_presence_of :name
  validates_length_of :name, :minimum => 1
  validates_uniqueness_of :name
  
  def self.find_or_create(name)
    category = self.find_by_name(name)
    if category == nil
      category = Category.new do |c|
        c.name = name
      end
      category.save!
    end
    category
  end
end
