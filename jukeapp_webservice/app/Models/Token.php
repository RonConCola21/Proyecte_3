<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Token extends Model
{
    use HasFactory;

    public $table = "active_token";

    public $timestamps = false;

    protected $fillable = [
        'at_id',
        'at_moment_temp',
        'at_cadena',
        'at_value'
    ];
}
