from schemas import HTTPMsg, Client, Receipt
from fastapi import APIRouter
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_bestpaymentmethod
import os, traceback

router = APIRouter()


@router.get("/payment_method")
def best_payment_method() -> HTTPMsg:
    message = None
    try:
        payment_method = fetch_data(os.getenv("API_RECEIPT") + "/tickets/mostUsedPaymentMethod")
        best_payment_method = unmarshall_bestpaymentmethod(payment_method)

        message = HTTPMsg(status=200, message="Success", content=best_payment_method)
        return message
    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
