from schemas import HTTPMsg, Client
from fastapi import APIRouter
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_client
import os, traceback


router = APIRouter()

@router.get("/customer/{customer_cardnumber}")
def customer(customer_cardnumber: str) -> HTTPMsg:
    message = None
    try:
        client = fetch_data(os.getenv("API_CLIENT") + f"/customer/{customer_cardnumber}")
        client = unmarshall_client(client[0])

        message = HTTPMsg(status=200, message="Success", content=client)
        return message

    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
