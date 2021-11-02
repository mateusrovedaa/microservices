<?php

namespace App\Http\Controllers;

use Laravel\Lumen\Routing\Controller as BaseController;

class Controller extends BaseController
{
    //
    protected function sendFormattedJsonResponse($data, $message = null, $status=200) {
        $success = true;
        return response()->json(compact('success','status','message','data'), $status);
    }

    protected function sendJsonErrorResponse($message = "Internal Server Error", $status=500) {
        $success = false;
        return response()->json(compact('success','status','message'), $status);
    }
}
