from schemas import HTTPMsg
from fastapi import APIRouter
from services.measure_client import ratio_women
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allclients
import os


router = APIRouter()


@router.get("/ratio_women")
def ratio_women() -> HTTPMsg:
    message = None
    try:
        data = fetch_data(os.getenv("API_RECEIPT"))

        receipts = unmarshall_allclients(data)
        result = ratio_women(receipts)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message
    except HTTPMsg as e:
        return e
