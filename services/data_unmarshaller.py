from schemas import HTTPMsg, Receipt, Client
from pydantic import ValidationError


def unmarshall_receipt(receipt_data) -> Receipt:
    try:
        receipt = Receipt(**receipt_data)
        return receipt
    except ValidationError as e:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les tickets de caisses",
            content={"error": str(e)},
        )


def unmarshall_client(client_data) -> Client:
    try:
        client = Client(**client_data)
        return client
    except ValidationError as e:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les clients",
            content={"error": str(e)},
        )


def unmarshall_allreceipts(allreceipts_data) -> Receipt:
    try:
        allreceipts = [Receipt(**receipt_data) for receipt_data in allreceipts_data]
        return allreceipts
    except ValidationError as e:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les tickets de caisses",
            content={"error": str(e)},
        )
    
def unmarshall_allclients(allclients_data) -> Client:
    try:
        allclients = [Client(**client_data) for client_data in allclients_data]
        return allclients
    except ValidationError as e:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les clients",
            content={"error": str(e)},
        )
