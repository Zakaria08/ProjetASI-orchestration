from schemas import HTTPMsg, Receipt, Client
from fastapi import APIRouter
from services.measure_client_receipt import all_receipts_per_client
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allreceipts, unmarshall_allclients
import os, traceback


router = APIRouter()


@router.get("/all_clients")
def all_clients() -> HTTPMsg:
    message = None
    try:
        client_list = fetch_data(os.getenv("API_CLIENT"))

        clients: list[Client] = unmarshall_allclients(client_list)
        result = all_clients(clients)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message

    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
