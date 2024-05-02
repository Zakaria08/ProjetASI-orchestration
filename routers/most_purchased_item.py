from schemas import HTTPMsg, Receipt
from fastapi import APIRouter
from services.measure_receipt import most_purchased_item
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allreceipts
import os


router = APIRouter()


@router.get("/mostpurchased")
def mostpurchased() -> HTTPMsg:
    message = None
    try:
        data = fetch_data(os.getenv("API_RECEIPT"))

        receipts : list[Receipt] = unmarshall_allreceipts(data)
        result = most_purchased_item(receipts)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message
    except HTTPMsg as e:
        return e
