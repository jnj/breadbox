# HomeBank CSV library
module HomeBank

  class HbTransaction < Struct.new(:day, :month, :year, :mode, :description, 
                                   :amount, :category)
    InternalTransferMode = 5
    
    def internal_transfer?
      mode == InternalTransferMode
    end
    
    def empty_category?
      category == nil || category.empty?
    end
  end

  ImportResult = Struct.new(:transactions, :errors)

  class TransactionCsvReader
    def initialize(lines)
      @lines = lines
    end

    def read
      errors = []
      records = []
      
      @lines.each do |line|
        begin
          records << read_csv_transaction(line)
        rescue ParseError => e
          errors << e.failed_line
        end
      end
      
      ImportResult.new(records, errors)
    end

    def read_csv_transaction(str)
      # date;mode;info;payee;description;amount;category
      # Values:
      # date  => format should be DD-MM-YY
      # mode  => from 0=none to 5=personal transfer
      # info  => a string (16car max.)
      # payee => a payee name
      # description  => a string (32car max.)
      # amount => a number with a '.' as decimal seperator, ex: -24.12 or 36.75
      # category => a category name
      if str =~ /^(\d{2})\/(\d{2})\/(\d{4});(\d);.*;.*;(.*);(-?[0-9.]+);(.*)$/
        day, month, year = $1.to_i, $2.to_i, $3.to_i
        mode = $4.to_i
        desc = $5
        amount = $6.to_f
        category = $7
        HbTransaction.new(day, month, year, mode, desc, amount, category)
      else
        raise ParseError.new(str)
      end
    end
  end

  class ParseError < StandardError
    def initialize(failed_line)
      super()
      @failed_msg = failed_msg
    end

    attr_reader :failed_line
  end
end
