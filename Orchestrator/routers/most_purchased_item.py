from schemas import HTTPMsg, Client, Receipt
from fastapi import APIRouter
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_mostpurchased
import os, traceback

router = APIRouter()


@router.get("/best_item")
def best_purchased_item() -> HTTPMsg:
    message = None
    try:
        most_purchased = fetch_data(os.getenv("API_RECEIPT") + "/tickets/mostPurchasedItem")
        best_purchased_item = unmarshall_mostpurchased(most_purchased)

        message = HTTPMsg(status=200, message="Success", content=best_purchased_item)
        return message
    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
