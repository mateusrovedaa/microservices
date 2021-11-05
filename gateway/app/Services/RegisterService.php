<?php

namespace App\Services;

use App\Traits\RequestService;

class RegisterService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.register.base_uri');
        //$this->secret = config('services.register.secret');
    }

    public function register($data)
    {
        return $this->request('POST', '/register', $data);
    }
}
