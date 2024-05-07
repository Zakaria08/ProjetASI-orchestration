from schemas import Client, HTTPMsg


def averageage(clients: list[Client]):
    if not clients:
        raise Exception("Error: Clients list is empty")
    else:
        total = 0
        for client in clients:
            total += client.age
        average = total / len(clients)
        return average


def ratiowomen(clients: list[Client]):
    if not clients:
        raise Exception("Error: Clients list is empty")
    else:
        totalwomen = 0
        for client in clients:
            if client.gender == "F":
                totalwomen += 1
        return (totalwomen / len(clients)) * 100 + "%"
