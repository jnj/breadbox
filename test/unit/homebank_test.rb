require 'lib/homebank'

class HbTransactionTest < Test::Unit::TestCase
  def test_internal_transfer
    t = HomeBank::HbTransaction.new
    t.mode = HomeBank::HbTransaction::InternalTransferMode
    assert t.internal_transfer?
  end
end

