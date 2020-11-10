from typing import Dict
import requests
import json


'''
This exercises of the book is proposed to be done with Postman or cURL.
For consume RESTful APIs HTTP requests, I prefered create a little script about.
'''

port = "8081"
url_api_endpoint = "http://localhost:{port}/api".format(port=port)


class CarDatabaseRequest(object):
    def __init__(self, *args):
        super(CarDatabaseRequest, self).__init__(*args)

    @staticmethod
    def __get_headers():
        headers = {
            'content-type': 'application/json',
            'Cache-Control': "no-cache"
        }
        return headers

    def __start_post_request(self, endpoint: str, payload: Dict):
        response = requests.post(
            url=endpoint,
            data=json.dumps(payload),
            headers=self.__get_headers(),
            verify=False
        )
        return response

    @staticmethod
    def __start_patch_request(endpoint: str, payload: Dict):
        response = requests.patch(
            url=endpoint,
            data=json.dumps(payload),
        )
        return response

    @staticmethod
    def __start_delete_request(endpoint: str):
        response = requests.delete(
            url=endpoint
        )
        return response

    def __end_request(self, response):
        print(response)
        self.json_printer(
            response.json()
        )
        return response.json()

    def get(self, endpoint):
        print(f"GET: {endpoint}")
        response = requests.get(
            url=endpoint
        )
        self.json_printer(response)
        return response.json()

    def post(self, endpoint, payload):
        print(f"POST: {endpoint}")
        response = self.__start_post_request(
            endpoint=endpoint,
            payload=payload
        )
        return self.__end_request(
            response=response
        )

    def patch(self, endpoint, payload):
        print(f"PATCH: {endpoint}")
        response = self.__start_patch_request(
            endpoint=endpoint,
            payload=payload
        )
        return self.__end_request(
            response=response
        )

    def delete(self, endpoint):
        print(f"\nDELETE: {endpoint}")
        response = self.__start_delete_request(
            endpoint=endpoint
        )
        print(response)
        print(response.text)
        return response
        

    def get_api(self):
        print(f"\nGET : API")
        json_response = self.get(
            url=url_api_endpoint
        )
        return json_response

    def get_cars(self):
        print(f"\nGET : Cars")
        json_response = requests.get(url="{url}/cars"
                                     .format(
                                         url=url_api_endpoint
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_owners(self):
        print(f"\nGET : Owners")
        json_response = requests.get(url="{url}/owners"
                                     .format(
                                         url=url_api_endpoint
                                     )
                                     ).json()

        self.json_printer(json_response)

    def get_car(self, car_id):
        print(f"\nGET : Car")
        json_response = requests.get(url="{url}/cars/{car_id}"
                                     .format(
                                         url=url_api_endpoint,
                                         car_id=car_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_owner(self, owner_id):
        print(f"\nGET : Owner")
        json_response = requests.get(url="{url}/owners/{owner_id}"
                                     .format(
                                         url=url_api_endpoint,
                                         owner_id=owner_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_owner_from_car(self, car_id):
        print(f"\nGET : Owner from car")
        json_response = requests.get(url="{url}/cars/{car_id}/owner"
                                     .format(
                                         url=url_api_endpoint,
                                         car_id=car_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_cars_from_owner(self, owner_id):
        print(f"\nGET : Cars from owner")
        json_response = requests.get(url="{url}/owners/{owner_id}/cars"
                                     .format(
                                         url=url_api_endpoint,
                                         owner_id=owner_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def post_car(self, params):
        print(f"\nPOST : Car")
        endpoint = ("{url_api_endpoint}/cars"
                    .format(
                        url_api_endpoint=url_api_endpoint
                    ))
        return self.post(
            endpoint=endpoint,
            payload=params
        )

    def post_owner(self, params):
        print(f"\nPOST : Owner")
        endpoint = ("{url_api_endpoint}/owners"
                    .format(
                        url_api_endpoint=url_api_endpoint
                    ))
        return self.post(
            endpoint=endpoint,
            payload=params
        )

    def patch_car(self, car_id, params):
        print(f"\nPATCH : Car")
        endpoint = ("{url_api_endpoint}/cars/{car_id}"
                    .format(
                        url_api_endpoint=url_api_endpoint,
                        car_id=car_id
                    ))
        return self.patch(
            endpoint=endpoint,
            payload=params
        )

    def patch_owner(self, owner_id, params):
        print(f"\nPATCH : Owner")
        endpoint = ("{url_api_endpoint}/owners/{owner_id}"
                    .format(
                        url_api_endpoint=url_api_endpoint,
                        owner_id=owner_id
                    ))
        return self.patch(
            endpoint=endpoint,
            payload=params
        )

    def delete_car(self, car_id):
        print(f"\nDELETE : Car")
        endpoint = ("{url_api_endpoint}/cars/{car_id}"
                    .format(
                        url_api_endpoint=url_api_endpoint,
                        car_id=car_id
                    ))
        return self.delete(
            endpoint=endpoint
        )

    @staticmethod
    def json_printer(json_obj):
        json_to_print = json.dumps(
            json_obj,
            indent=1
        )
        print(json_to_print)


def post_cars(carDBRequest: CarDatabaseRequest = CarDatabaseRequest):
    parameters = {
        "brand": "Lamborghini",
        "model": "Aventador",
        "color": "Black",
        "year": 2021,
        "price": 411000
    }
    carDBRequest.post_car(
        params=parameters
    )


def patch_cars(carDBRequest: CarDatabaseRequest = CarDatabaseRequest):
    endpoint = ("{url_api_endpoint}/owners/{owner_id}"
                .format(
                    url_api_endpoint=url_api_endpoint,
                    owner_id=1
                ))
    parameters = {
        "color": "Gray",
        "registerNumber": "IOI-101",
        "price": 707101,
        "owner": endpoint
    }
    carDBRequest.patch_car(
        car_id=6,
        params=parameters
    )


def delete_cars(carDBRequest: CarDatabaseRequest = CarDatabaseRequest):
    carDBRequest.delete_car(
        car_id=6
    )


def run_getters(carDBRequest: CarDatabaseRequest = CarDatabaseRequest()):
    # carDBRequest.get_api()
    carDBRequest.get_cars()
    # carDBRequest.get_owners()
    # carDBRequest.get_car(id=3)
    # carDBRequest.get_owner(id=1)
    # carDBRequest.get_owner_from_car(car_id=3)
    # carDBRequest.get_cars_from_owner(owner_id=2)


def run_posts(carDBRequest: CarDatabaseRequest = CarDatabaseRequest()):
    post_cars(carDBRequest)


def run_patches(carDBRequest: CarDatabaseRequest = CarDatabaseRequest()):
    patch_cars(carDBRequest)


def run_deletes(carDBRequest: CarDatabaseRequest = CarDatabaseRequest()):
    delete_cars(carDBRequest)


def main():
    # run_getters()
    # run_posts()
    # run_patches()
    # run_getters()
    run_deletes()


if __name__ == '__main__':
    main()
