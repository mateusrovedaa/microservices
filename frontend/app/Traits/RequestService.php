<?php

namespace App\Traits;

use GuzzleHttp\Client;

trait RequestService
{

    public function request($method, $requestUrl, $formParams = [], $headers = [])
    {
        $client = new Client([
            'base_uri' => $this->baseUri
        ]);
        if (isset($this->secret)) {
            $headers['Authorization'] = $this->secret;
        }
        $response = $client->request($method, $requestUrl,
            [
                'form_params' => $formParams,
                'headers' => $headers
            ]
        );
        return $response->getBody()->getContents();
    }

    public function requestGet($method, $requestUrl, $headers = [])
    {
        $client = new Client([
            'base_uri' => $this->baseUri
        ]);
        if (isset($this->secret)) {
            $headers['Authorization'] = $this->secret;
        }
        $response = $client->request($method, $requestUrl,
            [
                'headers' => $headers
            ]
        );
        return $response->getBody()->getContents();
    }

}
