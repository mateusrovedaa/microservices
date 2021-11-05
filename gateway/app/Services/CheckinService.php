<?php

namespace App\Services;

use App\Traits\RequestService;

class CheckinService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.checkin.base_uri');
        //$this->secret = config('services.register.secret');
    }

    public function checkin($data)
    {
        return $this->request('POST', '/checkin', $data);
    }
}
