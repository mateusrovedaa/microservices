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
        return $this->requestGet('GET', "/event/{$id}");
    }

    public function inscription($data)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        return $this->request('POST', '/event/registration', $data, $headers);
    }

    public function getInscriptions($data)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        $data = [
            'user_email' => Session::get('email')
        ];
        return $this->request('POST', "/event/inscriptions", $data, $headers);
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

    public function certificate($data)
    {
        $headers['Authorization'] = 'OAuth ' . Session::get( 'token');
        return $this->request('POST', '/certificate/certificate', $data, $headers);
    }

    public function validateCertificate($id)
    {
        return $this->request('GET', "/certificate/validate/{$id}");
    }

    public function getUser($email)
    {
        $data = [
            'email' => $email
        ];
        return $this->request('POST', "/users/single", $data);
    }
}
