from schemas import HTTPMsg, Client
from fastapi import APIRouter, Query
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allreceipts
import os, traceback


router = APIRouter()


@router.get("/ticket")
def customer(purchaseDate: str = Query(..., alias="purchaseDate")) -> HTTPMsg:
    message = None
    try:
        receipt = fetch_data(
            os.getenv("API_RECEIPT") + f"/ticket?purchaseDate={purchaseDate}"
        )
        result = unmarshall_allreceipts(receipt)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message

    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
