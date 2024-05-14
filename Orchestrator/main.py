from fastapi import FastAPI
from routers import (
    most_purchased_item,
    average_receipt,
    best_paymentmethod,
    average_clientage,
    all_receipts_per_client,
    all_clients,
)

app = FastAPI(title="ProjetASI Orchestrateur")

app.include_router(most_purchased_item.router)
app.include_router(average_receipt.router)
app.include_router(best_paymentmethod.router)
app.include_router(average_clientage.router)
app.include_router(all_receipts_per_client.router)
app.include_router(all_clients.router)
