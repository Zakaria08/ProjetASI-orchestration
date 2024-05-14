from schemas import HTTPMsg, Receipt
from fastapi import APIRouter
from services.measure_receipt import best_payment_method
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allreceipts
import os, traceback


router = APIRouter()


@router.get("/best_paymentmethod")
def best_paymentmethod() -> HTTPMsg:
    message = None
    try:
        data = fetch_data(os.getenv("API_RECEIPT")+ "/tickets/all")

        receipts : list[Receipt] = unmarshall_allreceipts(data)
        result = best_payment_method(receipts)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message
    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))