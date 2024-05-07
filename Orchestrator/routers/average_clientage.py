from schemas import HTTPMsg, Client, Receipt
from fastapi import APIRouter
from services.measure_client_receipt import average_client_age
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allclients, unmarshall_allreceipts
import os, traceback

router = APIRouter()


@router.get("/average_age")
def average_age() -> HTTPMsg:
    message = None
    try:
        receipt_list = fetch_data(os.getenv("API_RECEIPT"))
        client_list = fetch_data(os.getenv("API_CLIENT"))

        receipts: list[Receipt] = unmarshall_allreceipts(receipt_list)
        clients: list[Client] = unmarshall_allclients(client_list)
        result = average_client_age(receipts, clients)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message
    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
