from schemas import Receipt, Client
from dateutil import parser
from dateutil.relativedelta import relativedelta


def all_receipts_per_client(
    receipts: list[Receipt], clients: list[Client]
) -> list[Client]:
    if not receipts:
        return Exception("Error: Receipt list is empty")
    if not clients:
        return Exception("Error: Client list is empty")

    for client in clients:
        client.receipts = [
            receipt for receipt in receipts if receipt.cardNumber == client.cardnumber
        ]

    return clients


def average_client_age(receipts: list[Receipt], clients: list[Client]) -> float:
    if not receipts:
        return Exception("Error: Receipt list is empty")
    if not clients:
        return Exception("Error: Client list is empty")

    age = 0
    count = 0
    for receipt in receipts:
        for client in clients:
            if receipt.cardNumber == client.cardnumber:
                date_naissance = parser.parse(client.birthday)
                date_transaction = parser.parse(receipt.purchaseDate, dayfirst=True)

                if date_naissance.tzinfo:
                    date_naissance = date_naissance.replace(tzinfo=None)
                if date_transaction.tzinfo:
                    date_transaction = date_transaction.replace(tzinfo=None)
               
                difference = relativedelta(date_transaction, date_naissance)
                age += difference.years
                count += 1
    return format((age / count), ".2f")
