#include "PineBorscht.h"

PineBorscht::PineBorscht(World &wR, const int &xPos, const int &yPos)
    : Plant(wR, xPos, yPos, SYMBOL, STRENGTH, AllOrganisms::PINEBORSCHT) { this->typeS = "Barszcz sosnowskiego"; }
PineBorscht::~PineBorscht() {}
void PineBorscht::takeAction()
{
    if (this->worldRef.isPositionFree(this->xPos, this->yPos - 1) != true && this->worldRef.isPositionInsideWorld(this->xPos, this->yPos - 1))
    {
        Organism *org = this->worldRef.getOrganism(this->xPos, this->yPos - 1);
        if (org->isAnAnimal())
        {
            org->die();
            string event = org->getTypeString() + " zostaje spalony przez barszcz sosnowskiego";
            this->worldRef.addEvent(event);
        }
    }
    else if (this->worldRef.isPositionFree(this->xPos, this->yPos + 1) != true && this->worldRef.isPositionInsideWorld(this->xPos, this->yPos + 1))
    {
        Organism *org = this->worldRef.getOrganism(this->xPos, this->yPos + 1);
        if (org->isAnAnimal())
        {
            org->die();
            string event = org->getTypeString() + " zostaje spalony przez barszcz sosnowskiego";
            this->worldRef.addEvent(event);
        }
    }
    else if (this->worldRef.isPositionFree(this->xPos + 1, this->yPos) != true && this->worldRef.isPositionInsideWorld(this->xPos + 1, this->yPos))
    {
        Organism *org = this->worldRef.getOrganism(this->xPos + 1, this->yPos);
        if (org->isAnAnimal())
        {
            org->die();
            string event = org->getTypeString() + " zostaje spalony przez barszcz sosnowskiego";
            this->worldRef.addEvent(event);
        }
    }
    else if (this->worldRef.isPositionFree(this->xPos - 1, this->yPos) != true && this->worldRef.isPositionInsideWorld(this->xPos - 1, this->yPos))
    {
        Organism *org = this->worldRef.getOrganism(this->xPos - 1, this->yPos);
        if (org->isAnAnimal())
        {
            org->die();
            string event = org->getTypeString() + " zostaje spalony przez barszcz sosnowskiego";
            this->worldRef.addEvent(event);
        }
    }
    if (rand() % 100 == 0)
        trySpread();
}
bool PineBorscht::specialAbility(Organism *other)
{
    other->die();
    this->die();
}