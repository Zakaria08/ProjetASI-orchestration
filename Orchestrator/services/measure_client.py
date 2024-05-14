from schemas import Client, HTTPMsg


def all_clients(clients: list[Client]) -> list[Client]:
    if not clients:
        raise Exception("Error: Clients list is empty")
    return clients