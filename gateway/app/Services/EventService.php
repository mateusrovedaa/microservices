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
        return $this->request('GET', '/events');
    }

    public function getSingleEvent($id)
    {
        return $this->request('GET', "/event/{$id}");
    }

    public function getInscriptions()
    {
        return $this->request('GET', '/inscriptions');
    }

    public function getSingleInscription($email)
    {
        return $this->request('GET', "/inscriptions/{$email}");
    }
}
