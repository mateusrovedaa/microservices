<?php

namespace App\Services;

use App\Traits\RequestService;

class EmailService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.email.base_uri');
        //$this->secret = config('services.register.secret');
    }

    public function sendEmail($data)
    {
        return $this->request('POST', '/api/v1/send/email', $data);
    }
}
