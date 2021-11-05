<?php

namespace App\Services;

use App\Traits\RequestService;

class EventService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.event.base_uri');
        //$this->secret = config('services.login.secret');
    }

    public function registration($data)
    {
        return $this->request('POST', '/registration', $data);
    }

    public function cancelRegistration($data)
    {
        return $this->request('POST', '/cancelregistration', $data);
    }

    public function getEvents()
    {
        return $this->request('GET', '/inscriptions');
    }

    public function getSingleEvents($id)
    {
        return $this->request('GET', "/inscriptions/{$id}");
    }
}
