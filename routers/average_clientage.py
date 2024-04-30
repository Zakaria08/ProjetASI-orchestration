from schemas import HTTPMsg
from fastapi import APIRouter
from services.measure_client import average_age
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allclients
import os


router = APIRouter()


@router.get("/average_age")
def average_age() -> HTTPMsg:
    message = None
    try:
        data = fetch_data(os.getenv("API_RECEIPT"))

        receipts = unmarshall_allclients(data)
        result = average_age(receipts)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message
    except HTTPMsg as e:
        return e
