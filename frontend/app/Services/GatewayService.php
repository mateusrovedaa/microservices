<?php

namespace App\Services;

use App\Traits\RequestService;
use Illuminate\Support\Facades\Session;
class GatewayService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.gateway.base_uri');
        //$this->secret = config('services.login.secret');
    }

    public function login($data)
    {
        return $this->request('POST', '/login', $data);
    }

    public function register($data)
    {
        return $this->request('POST', '/register', $data);
    }

    public function getEvents($data)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        return $this->request('GET', '/event/', $data, $headers);
    }

    public function getSingleEvent($id)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        return $this->requestGet('GET', "/event/{$id}", $headers);
    }

    public function inscription($data)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        return $this->request('POST', '/event/registration', $data, $headers);
    }

    public function getInscriptions()
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        $email = Session::get('email');
        return $this->requestGet('GET', "/event/inscriptions/{$email}", $headers);
    }

    public function cancelInscription($data)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        return $this->request('POST', '/event/cancelregistration', $data, $headers);
    }

    public function sendEmail($data)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        return $this->request('POST', '/email/', $data, $headers);
    }
}
