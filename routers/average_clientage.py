from schemas import HTTPMsg, Client
from fastapi import APIRouter
from services.measure_client import averageage
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_allclients
import os


router = APIRouter()


@router.get("/average_age")
def average_age() -> HTTPMsg:
    message = None
    try:
        data = fetch_data(os.getenv("API_RECEIPT"))

        clients : list[Client] = unmarshall_allclients(data)
        result = averageage(clients)

        message = HTTPMsg(status=200, message="Success", content=result)
        return message
    except HTTPMsg as e:
        return e
