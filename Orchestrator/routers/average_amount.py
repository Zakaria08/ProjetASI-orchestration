from schemas import HTTPMsg, Client, Receipt
from fastapi import APIRouter
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_sum
import os, traceback

router = APIRouter()


@router.get("/average_amount")
def average_amount() -> HTTPMsg:
    message = None
    try:
        sum = fetch_data(os.getenv("API_RECEIPT") + "/tickets/averageAmount")
        sum = unmarshall_sum(sum)

        message = HTTPMsg(status=200, message="Success", content=sum)
        return message
    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
