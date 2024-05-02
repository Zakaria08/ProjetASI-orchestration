from schemas import HTTPMsg, Receipt

{
    "id": 6,
    "receiptNumber": 6,
    "purchaseDate": "12.05.2022",
    "itemsPurchased": "Jambon",
    "quantity": 5,
    "pricePerItem": 7.5,
    "totalAmount": 37.50,
    "paymentMethod": "debit card",
    "storeLocation": "Chaux-de-Fonds",
    "cashierName": "Victor",
    "cardNumber": "4716258050958645",
}


def get_receipts_client(card_nummer: int, receipts: list[Receipt]) -> list[Receipt]:
    return [receipt for receipt in receipts if receipt.cardNumber == card_nummer]


## get the total amount spent by a specific client
def total_amount_spent(card_nummer: int, receipts: list[Receipt]) -> float:
    return sum(
        [receipt.totalAmount for receipt in get_receipts_client(card_nummer, receipts)]
    )
