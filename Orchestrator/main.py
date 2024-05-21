from fastapi import FastAPI
from routers import (
    all_receipts_per_client,
    all_clients,
    specific_client,
    specific_receipt,
    receipt_of_day,
    average_client_age,
    month_year_sum,
    most_purchased_item,
    most_used_payment_method,
    average_amount,
    year_sum
)

app = FastAPI(title="ProjetASI Orchestrateur")

app.include_router(average_client_age.router)
app.include_router(all_receipts_per_client.router)
app.include_router(all_clients.router)
app.include_router(specific_client.router)
app.include_router(specific_receipt.router)
app.include_router(receipt_of_day.router)
app.include_router(month_year_sum.router)
app.include_router(most_purchased_item.router)
app.include_router(most_used_payment_method.router)
app.include_router(average_amount.router)
app.include_router(year_sum.router)
