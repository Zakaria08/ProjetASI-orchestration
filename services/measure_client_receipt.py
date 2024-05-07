from schemas import Receipt


def get_receipts_client(card_nummer: int, receipts: list[Receipt]) -> list[Receipt]:
    return [receipt for receipt in receipts if receipt.cardNumber == card_nummer]


def total_amount_spent(card_nummer: int, receipts: list[Receipt]) -> float:
    return sum(
        [receipt.totalAmount for receipt in get_receipts_client(card_nummer, receipts)]
    )
