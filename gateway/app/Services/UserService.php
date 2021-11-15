<?php

namespace App\Services;

use App\Traits\RequestService;

class UserService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.login.base_uri');
        //$this->secret = config('services.login.secret');
    }

    public function getUsers()
    {
        return $this->request('GET', '/users');
    }

    public function getSingle($data)
    {
        return $this->request('POST', '/user', $data);
    }

}
