FROM python:3.7

COPY requirements.txt /
RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r /requirements.txt
RUN pip list

EXPOSE 5000