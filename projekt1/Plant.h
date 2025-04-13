#pragma once
#include "Organism.h"
#define PLANT_INITIATIVE 0

class Plant : public Organism
{
public:
    Plant(World &wR, const int &xPos, const int &yPos, const char &symbol, const int &strength, AllOrganisms type);
    ~Plant();
    virtual void takeAction() override;
    virtual bool isAnAnimal() const override;

protected:
    void trySpread();
};