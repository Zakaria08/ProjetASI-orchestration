from schemas import Client, HTTPMsg


def all_clients(clients: list[Client]) -> list[Client]:
    if not clients:
        raise Exception("Error: Clients list is empty")
    return clients

def ratiowomen(clients: list[Client]):
    if not clients:
        raise Exception("Error: Clients list is empty")
    else:
        totalwomen = 0
        for client in clients:
            if client.gender == "F":
                totalwomen += 1
        return (totalwomen / len(clients)) * 100 + "%"
