<?php

namespace App\Services;

use App\Traits\RequestService;

class CertificateService
{
    use RequestService;

    public $baseUri;
    public $secret;

    public function __construct()
    {
        $this->baseUri = config('services.certificate.base_uri');
        //$this->secret = config('services.login.secret');
    }

    public function generate($data)
    {
        return $this->request('POST', '/certificate', $data);
    }

    public function validate($certificate)
    {
        return $this->request('GET', "/validate/{$certificate}");
    }
}
