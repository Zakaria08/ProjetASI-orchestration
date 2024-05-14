from schemas import HTTPMsg, Client
from fastapi import APIRouter
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_receipt
import os, traceback


router = APIRouter()


@router.get("/ticket/{receipt_id}")
def customer(receipt_id: str) -> HTTPMsg:
    message = None
    try:
        receipt = fetch_data(os.getenv("API_RECEIPT") + f"/ticket/{receipt_id}")
        result = unmarshall_receipt(receipt)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message

    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
