#pragma once
#include "Animal.h"

#define STRENGTH 9
#define INITIATIVE 5
#define SYMBOL 'W'

class Wolf: public Animal
{
    public:
    Wolf(World &wR, const int &xPos, const int &yPos);
    ~Wolf();
};