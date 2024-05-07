from schemas import Receipt, Client
from pydantic import ValidationError


def unmarshall_receipt(receipt_data) -> Receipt:
    try:
        receipt = Receipt(**receipt_data)
        return receipt
    except ValidationError as e:
        raise Exception(
            f"The server encountered an error while trying to retrieve the receipt: {e}"
        )


def unmarshall_client(client_data) -> Client:
    try:
        client = Client(**client_data)
        return client
    except ValidationError as e:
        Exception(
            f"The server encountered an error while trying to retrieve information about the client: {e}"
        )


def unmarshall_allreceipts(allreceipts_data) -> list[Receipt]:
    try:
        allreceipts = [Receipt(**receipt_data) for receipt_data in allreceipts_data]
        return allreceipts
    except ValidationError as e:
        raise Exception(
            f"The server encountered an error when trying to retrieve information about the receipt list: {e}"
        )


def unmarshall_allclients(allclients_data) -> Client:
    try:
        allclients = [Client(**client_data) for client_data in allclients_data]
        return allclients
    except ValidationError as e:
        raise Exception(
            f"The server encountered an error when trying to retrieve information about the customer list 'unmarshall_allclients': {e}"
        )
