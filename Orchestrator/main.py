from fastapi import FastAPI
from routers import (
    all_receipts_per_client,
    all_clients,
    specific_client,
    specific_receipt,
    receipt_of_day,
    average_client_age
)

app = FastAPI(title="ProjetASI Orchestrateur")

app.include_router(average_client_age.router)
app.include_router(all_receipts_per_client.router)
app.include_router(all_clients.router)
app.include_router(specific_client.router)
app.include_router(specific_receipt.router)
app.include_router(receipt_of_day.router)
