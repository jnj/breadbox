require 'hpricot'
require 'open-uri'

class CacheEntry
  def initialize(price)
    @price = price
    @exp = 10.minutes.from_now
  end
  attr_accessor :price, :exp
end

class Stock < ActiveRecord::Base
  belongs_to :account
  @@cached_quotes = {}

  def self.fetch_stock_quote(symbol)
    if @@cached_quotes.has_key?(symbol) && 
        @@cached_quotes[symbol].exp > Time.now
      @@cached_quotes[symbol].price
    else
      url_str = "http://finance.google.com/finance?q=#{symbol.downcase}"
      doc = open(url_str) { |f| Hpricot(f) }
      elem = doc.at("//span[@class='pr']")
      quote = elem.inner_html.to_f
      @@cached_quotes[symbol] = CacheEntry.new(quote)
      quote
    end
  end
  
  def price
    quote = Stock.fetch_stock_quote(symbol)
  end
end
