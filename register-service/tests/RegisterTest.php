<?php

use Laravel\Lumen\Testing\DatabaseTransactions;

class RegisterTest extends TestCase
{
    use DatabaseTransactions;
    /**
     * A basic test example.
     *
     * @return void
     */
    public function testShouldRegisterANewUser()
    {
        $params = [
            'name' => 'Ade Kingston',
            'email' => 'user@gmail.com',
            'password' => 'mypassword'
        ];

        $this->post('/auth/register', $params, [])
             ->seeStatusCode(201);
    }

    public function testShouldNotRegisterUserWithInvalidEmail()
    {
        $params = [
            'name' => 'Ade Kingston',
            'email' => 'invalid',
            'password' => 'mypassword'
        ];

        $this->post('/auth/register', $params, [])
             ->seeStatusCode(400);
    }

}
