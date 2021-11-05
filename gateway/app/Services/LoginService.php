<?php

namespace App\Services;

use App\Traits\RequestService;

class LoginService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.login.base_uri');
        //$this->secret = config('services.login.secret');
    }

    public function login($data)
    {
        return $this->request('POST', '/login', $data);
    }
}
