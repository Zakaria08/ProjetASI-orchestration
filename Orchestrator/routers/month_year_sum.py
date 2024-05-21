from schemas import HTTPMsg
from fastapi import APIRouter
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_sum
import os, traceback

router = APIRouter()


@router.get("/sum/{year}/{month}")
def sum_month_year(year: str, month: str) -> HTTPMsg:
    message = None
    try:
        sum = fetch_data(os.getenv("API_RECEIPT") + f"/tickets/sum/{year}/{month}")
        sum = unmarshall_sum(sum)

        message = HTTPMsg(status=200, message="Success", content=sum)
        return message
    except Exception as exc:
        print(traceback.format_exc())
        return HTTPMsg(status=500, message="Erreur Serveur", content=str(exc))
